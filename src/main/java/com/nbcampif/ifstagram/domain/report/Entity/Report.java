package com.nbcampif.ifstagram.domain.report.Entity;


import com.nbcampif.ifstagram.domain.user.model.User;
import com.nbcampif.ifstagram.global.entity.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "report")
public class Report extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private Long from_user_id;

    @Column
    private Long to_user_id;

    public Report(Long to_user_id, Long from_user_id, String result){
        this.to_user_id = to_user_id;
        this.from_user_id = from_user_id;
        this.content = result;
    }

}
