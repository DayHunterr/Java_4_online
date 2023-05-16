package ua.com.alevel.controller;

import com.opencsv.CSVWriter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dao.TransactionCheckDao;
import ua.com.alevel.dto.TransactionCheckDTO;


import java.io.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionCheckController {

    private final TransactionCheckDao transactionCheckDao;


    @GetMapping("/download/{id}")
    @CrossOrigin(origins = "http://localhost:8081")
    public void downloadFile(HttpServletResponse response,@PathVariable Long id) throws IOException {

        List<TransactionCheckDTO> transactionCheckDTOS = transactionCheckDao.transaction(id); // Здесь можете использовать нужные методы репозитория для выборки данных

        // Создание временного файла
        File tempFile = File.createTempFile("transactions", ".csv");
        FileWriter fileWriter = new FileWriter(tempFile);
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        csvWriter.writeNext(new String[]{"Id", "Created", "Amount","Category", "Description"});

        // Запись данных о пользователях
        for (TransactionCheckDTO transactionCheckDTO : transactionCheckDTOS) {
            csvWriter.writeNext(new String[]{String.valueOf(transactionCheckDTO.getId()),
                    String.valueOf(transactionCheckDTO.getCreated()),
                    String.valueOf(transactionCheckDTO.getAmount()),
                    String.valueOf(transactionCheckDTO.getType()),
                    transactionCheckDTO.getDescription()});
        }
        csvWriter.close();

        // Настройка заголовков ответа
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=checks.csv");

        // Копирование файла в ответ
        InputStream inputStream = new FileInputStream(tempFile);
        OutputStream outputStream = response.getOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        inputStream.close();
        outputStream.close();

        // Удаление временного файла
        tempFile.delete();


    }
}
