package com.nbcampif.ifstagram.domain.report.repository;

import com.nbcampif.ifstagram.domain.report.Entity.Report;
import com.nbcampif.ifstagram.domain.user.dto.ReportReponseDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

  List<Report> findAllByTo_user_id(Long reportId);
}
