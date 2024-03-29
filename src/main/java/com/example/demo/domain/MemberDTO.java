package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "member")
public class MemberDTO { // 회원 클래스
    @Id @GeneratedValue // @GeneratedValue : 자동 생성 => 시퀀스 값 같은
    @Column(name = "member_id")
    private Long id; // 임의로 사용할 키값
    @Column(name = "student_id")
    private int studentId; // 학번
    private String name; // 회원 이름
    @Enumerated(EnumType.STRING) // 데이터값을 int가 아닌 String으로 나오게 함
    private Access access; // 주어질 권한
    @Enumerated(EnumType.STRING) // 데이터값을 int가 아닌 String으로 나오게 함
    private Login login; // 주어질 권한
    @OneToMany(mappedBy = "member") // mappedBy : 연관관계 주인이 누구인지 상태 테이블 속성이름으로 명시해줌
    private List<BoardDTO> board = new ArrayList<>();
    @OneToMany(mappedBy = "member") // mappedBy : 연관관계 주인이 누구인지 상태 테이블 속성이름으로 명시해줌
    private List<CommentDTO> comments = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<TimetableDTO> timetable = new ArrayList<>(); // 한 명의 사용자는 여러 시간표를 가질 수 있음
    @OneToMany(mappedBy = "member") 
    private List<NotificationDTO> notifications = new ArrayList<>(); // 한 명의 사용자는 여러 알림을 받음
}
