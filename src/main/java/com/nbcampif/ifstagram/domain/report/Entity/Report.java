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
    private Long fromUserId;

    @Column
    private Long toUserId;

    public Report(Long toUserId, Long fromUserId, String result){
        this.toUserId = toUserId;
        this.fromUserId = fromUserId;
        this.content = result;
    }

}
