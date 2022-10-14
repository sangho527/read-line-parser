package com.line;

import com.line.domain.Hospital;
import com.line.parser.HospitalParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FileController<Hospital> hospitalFileController = new FileController<>(new HospitalParser());
        String filename = "C:\\Users\\PC\\Downloads\\seoul_hospital_infos.csv";
        List<Hospital> hospitals = hospitalFileController.readLines(filename);

        List<String> sqlStatements = new ArrayList<>();
        for (Hospital hospital : hospitals){
            sqlStatements.add(hospital.getSqlInsertQuery2());
        }
        String sqlFilename = "seoul_hospital_insert.sql"; // 이 이름을 가진 sql 파일을 만들고
        hospitalFileController.createANewFile(sqlFilename);
        hospitalFileController.writeLines(sqlStatements, sqlFilename); // 이 파일네임에 저장해라



//        System.out.println(hospitals.size());
//        List<String> lines = new ArrayList<>();
//        for (Hospital hospital : hospitals) {
//            lines.add(hospital.getSqlInsertQuery());
//        }
//        String sqlFilename = "hospital_insert.sql";
//        hospitalFileController.createANewFile(sqlFilename);
//        hospitalFileController.writeLines(lines, sqlFilename); // 10월 13일까지 진행과정

    }
}