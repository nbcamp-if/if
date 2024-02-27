package com.nbcampif.ifstagram.domain.report.repository;

import com.nbcampif.ifstagram.domain.report.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
