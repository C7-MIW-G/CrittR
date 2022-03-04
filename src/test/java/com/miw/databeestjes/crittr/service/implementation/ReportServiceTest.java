package com.miw.databeestjes.crittr.service.implementation;

import com.miw.databeestjes.crittr.model.Report;
import com.miw.databeestjes.crittr.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @InjectMocks
    private ReportServiceImplementation testServiceImplementation;

    @Mock
    private ReportRepository testRepository;

    private Report testReport;

    @BeforeEach
    public void setup() {
        testServiceImplementation = new ReportServiceImplementation(testRepository);
        testReport = new Report();
        testReport.setReportId(-1);
    }

    @Test
    public void addReportShouldReturnAddedReport() {
        when(testRepository.save(any(Report.class))).thenReturn(new Report());

        testReport.setIssue("TestIssue");
        testServiceImplementation.save(testReport);

        assertEquals("TestIssue", testReport.getIssue());
    }
}
