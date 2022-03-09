package io.bitscope.example.hr.service;

import io.bitscope.example.hr.entity.Budget;
import io.bitscope.example.hr.model.Employee;
import io.bitscope.example.hr.repository.EmployeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public byte[] generateReportPdf() throws JRException, IOException {
        List<Employee> employeeCollection = employeeRepository.findAll();
        List<Budget> budgetCollection = new ArrayList<Budget>();
        Map<String, BigDecimal> memory = new HashMap<String, BigDecimal>();
        employeeCollection.forEach(employee -> {
            if (employee.getDepartment() != null) {
                if (memory.containsKey(employee.getDepartment().getDepartmentName())) {
                    BigDecimal spending = memory.get(employee.getDepartment().getDepartmentName());
                    spending = spending.add(employee.getSalary());
                    memory.replace(employee.getDepartment().getDepartmentName(), spending);
                } else {
                    memory.put(employee.getDepartment().getDepartmentName(), employee.getSalary());
                }
            }
        });

        memory.forEach((k, v) -> budgetCollection.add(new Budget(k, v)));

        String masterReportLocation = "classpath:Employees.jrxml";
        String subReportLocation = "classpath:Budgets.jrxml";

        JasperPrint jasperPrint = getJasperPrint(employeeCollection, budgetCollection, masterReportLocation, subReportLocation);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private JasperPrint getJasperPrint(List<Employee> employeeCollection, List<Budget> budgetCollection, String masterReportLocation, String subReportLocation) throws FileNotFoundException, JRException {
        File employeesReportFile = ResourceUtils.getFile(masterReportLocation);
        File budgetsReportFile = ResourceUtils.getFile(subReportLocation);

        JasperReport masterJasperReport = JasperCompileManager.compileReport(employeesReportFile.getAbsolutePath());
        JasperReport subJasperReport = JasperCompileManager.compileReport(budgetsReportFile.getAbsolutePath());

        JRBeanCollectionDataSource employeeDataSource = new JRBeanCollectionDataSource(employeeCollection);
        JRBeanCollectionDataSource budgetDataSource = new JRBeanCollectionDataSource(budgetCollection);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("role", "Admin");
        parameters.put("employees", employeeDataSource);
        parameters.put("budgetReport", subJasperReport);
        parameters.put("budgets", budgetDataSource);

        JasperPrint jasperPrint = JasperFillManager.fillReport(masterJasperReport, parameters, new JREmptyDataSource());

        return jasperPrint;
    }
}
