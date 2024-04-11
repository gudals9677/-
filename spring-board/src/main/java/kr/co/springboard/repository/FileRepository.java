package kr.co.springboard.repository;

import kr.co.springboard.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

    List<File> findByAno(int ano);
}
