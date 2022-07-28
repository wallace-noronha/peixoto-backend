package br.com.peixotoinstalacoes.controleestoque.service;

import br.com.peixotoinstalacoes.controleestoque.model.Orcamento;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.json.JRJsonNode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GenerateReport {

    public byte[] generateReport(Orcamento orcamento) {
        try {

            ObjectMapper mapper = new ObjectMapper();
            InputStream json = new ByteArrayInputStream(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(orcamento).getBytes("UTF-8"));

            Resource resource = new ClassPathResource("orcamento.jrxml");

            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());

            // Get your data source
            JsonDataSource jsonDataSource = new JsonDataSource(json);
            // Add parameters
            Map<String, Object> parameters = new HashMap<>();

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,jsonDataSource);

            File file = File.createTempFile("report",".pdf");

            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, file.getPath());
            byte[] bit = Files.readAllBytes(file.toPath());

            file.delete();

            return bit;

        } catch (Exception e) {
            return null;
        }
    }

}
