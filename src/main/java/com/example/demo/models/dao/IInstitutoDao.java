package com.example.demo.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.entity.Instituto;

public interface IInstitutoDao extends JpaRepository<Instituto, Long> {

}
