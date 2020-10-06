package com.springproject.universityinfoconvertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class InfoProcessor {

    private final String csvHeader = "First Name [Required],Last Name [Required],Email Address [Required],Password [Required],Password Hash Function [UPLOAD ONLY],Org Unit Path [Required],New Primary Email [UPLOAD ONLY],Recovery Email,Home Secondary Email,Work Secondary Email,Recovery Phone [MUST BE IN THE E.164 FORMAT],Work Phone,Home Phone,Mobile Phone,Work Address,Home Address,Employee ID,Employee Type,Employee Title,Manager Email,Department,Cost Center,Building ID,Floor Name,Floor Section,Change Password at Next Sign-In,New Status [UPLOAD ONLY]\n";
    private final String adminMail = "kick.maks545@icloud.com";
    private final String adminNumber = "+38095111844";
    private final String orgUnitPath = "/";
    private final String changePasswordAtNextSignIn = "0";


    private List<StudentInfo> studentInfoList;
    private File csvFile;

    @Value("${info.pathToCsv}")
    private String pathToCsv;

    private Transliterator transliterator;
    private EmailGenerator emailGenerator;

    public List<StudentInfo> getStudentInfoList() {
        return studentInfoList;
    }

    public File getCsvFile() {
        return csvFile;
    }

    @Autowired
    public InfoProcessor(Transliterator transliterator, EmailGenerator emailGenerator) {
        this.transliterator = transliterator;
        this.emailGenerator = emailGenerator;
        readCsvFile();
        generateCsvFile();
    }

    private void readCsvFile() {
        studentInfoList = new ArrayList<>();
        try {
            BufferedReader csvReader = null;
            //System.out.println(pathToCsv);
            csvReader = new BufferedReader(new FileReader("src/main/resources/StudentInfo.csv"));

            String row = "";
            while ((row = csvReader.readLine()) != null) {
                String name = transliterator.getTranslatedString(row);
                String email = checkEmailUniqueness(emailGenerator.getGeneratedEmail(name));
                String password = emailGenerator.getGeneratedPassword();
                studentInfoList.add(new StudentInfo(row, name, email, password));
            }
            csvReader.close();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    private void generateCsvFile() {
        csvFile = new File("OutputStudentInfo.csv");
        try (PrintWriter writer = new PrintWriter(csvFile)) {

            String line = new String();
            line += csvHeader;
            for (StudentInfo studentInfo : studentInfoList) {
                String[] fullName = studentInfo.getNameUKR().split(" ");
                String firstName = fullName[1] + (fullName.length == 3 ? " " + fullName[2] : "");
                line += firstName + "," +fullName[0] + "," + studentInfo.getEmail() + "," + studentInfo.getPassword() + ",," +
                        orgUnitPath + ",," + adminMail + ",,,,," + adminNumber + ",,,,,,,,,,,,," + changePasswordAtNextSignIn + ",\n";
            }
            writer.write(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String checkEmailUniqueness(String email) {
        if(studentInfoList.stream().filter(o -> o.getEmail().equals(email)).findFirst().isPresent()){
            String random = new Random().ints(4, 48, 57).mapToObj(i ->
                    String.valueOf((char)i)).collect(Collectors.joining());
            String[] splitEmail = email.split("@");
            return  splitEmail[0] + random + "@" + splitEmail[1];
        }
        return email;
    }
}
