# Membership Management

## 기능

1. 회원등록
2. 회원조회
   - 모든 회원조회
   - 개인 회원조회
3. 프로그램 종료

- ## 요구사항
  - ### DB 생성
  ```
  drop database if exists memberdb;
  create database memberdb;
  use memberdb;
  ```
  - ### table 생성
  ```
  create table member(
  number int auto_increment primary key,
  name varchar(10),
  id varchar(20),
  pwd varchar(20),
  age int,
  area varchar(5),
  date date
  );
  ```
