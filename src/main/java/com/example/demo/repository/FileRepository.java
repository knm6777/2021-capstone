package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FileRepository extends JpaRepository<FileModel, Integer> {

}
