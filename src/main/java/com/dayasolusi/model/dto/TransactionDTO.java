package com.dayasolusi.model.dto;

import com.dayasolusi.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {
    @NotNull(message = "product name cannot be empty")
    private String productName;

    @NotNull(message = "amount cannot be empty")
    private String amount;

    @NotNull(message = "customer cannot be empty")
    private String customerName;

    private Status status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "transaction date cannot be empty")
    private LocalDateTime transactionDate;

    @NotNull(message = "create by cannot be empty")
    private String createBy;
}
