package com.gerwais.ekwateurkata.application;

import com.gerwais.ekwateurkata.application.dto.*;
import com.gerwais.ekwateurkata.application.mapper.BillMapper;
import com.gerwais.ekwateurkata.application.mapper.ClientMapper;
import com.gerwais.ekwateurkata.domain.model.billing.Bill;
import com.gerwais.ekwateurkata.domain.model.client.Client;
import com.gerwais.ekwateurkata.domain.usecase.billing.BillProcessCommand;
import com.gerwais.ekwateurkata.domain.usecase.billing.port.BillProcessingUsecase;
import com.gerwais.ekwateurkata.domain.usecase.client.UserCreationCommand;
import com.gerwais.ekwateurkata.domain.usecase.client.port.ClientCreationUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientCreationUsecase clientCreationUsecase;
    private final BillProcessingUsecase billProcessingUsecase;

    public ClientController(ClientCreationUsecase clientCreationUsecase, BillProcessingUsecase billProcessingUsecase) {
        this.clientCreationUsecase = clientCreationUsecase;
        this.billProcessingUsecase = billProcessingUsecase;
    }

    @PostMapping("/professional")
    public ResponseEntity<ProClientDto> createProClient(@Valid @RequestBody ProClientDto clientBody) {
        UserCreationCommand command = ClientMapper.toCommand(clientBody);
        Client client = clientCreationUsecase.apply(command);
        ProClientDto body = ClientMapper.toProDto(client);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PostMapping("/individual")
    public ResponseEntity<IndivClientDto> createClient(@Valid @RequestBody IndivClientDto clientBody) {
        UserCreationCommand command = ClientMapper.toCommand(clientBody);
        Client client = clientCreationUsecase.apply(command);
        IndivClientDto body = ClientMapper.toIndivDto(client);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }


    @PostMapping("/{refClient}/bill")
    public ResponseEntity<BillDto> createClient(@PathVariable String refClient, @RequestBody BillBodyDto billBodyDto) {

        BillProcessCommand command = BillMapper.toCommand(billBodyDto);
        Bill bill = billProcessingUsecase.apply(command);
        BillDto body = BillMapper.toDto(bill);
        return new ResponseEntity<>(body, HttpStatus.OK);

    }


}
