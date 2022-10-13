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

        System.out.println(hospitals.size());
        List<String> lines = new ArrayList<>();
        for (Hospital hospital : hospitals) {
            lines.add(hospital.getSqlInsertQuery());
        }
        String sqlFilename = "hospital_insert.sql";
        hospitalFileController.createANewFile(sqlFilename);
        hospitalFileController.writeLines(lines, sqlFilename);

    }
}