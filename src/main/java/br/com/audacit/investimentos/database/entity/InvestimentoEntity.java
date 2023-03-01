package br.com.audacit.investimentos.database.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@DynamoDBTable(tableName = "investimento")
public class InvestimentoEntity {

    @DynamoDBAutoGeneratedKey
    @DynamoDBHashKey(attributeName = "codigo_movimentacao")
    private UUID codigoMovimentacao;

    @DynamoDBRangeKey(attributeName = "codigo_cliente")
    @DynamoDBIndexHashKey(attributeName = "codigo_cliente", globalSecondaryIndexNames = {"gsi_busca_cliente_por_mes", "gsi_busca_cliente_por_data"})
    private UUID codigoCliente;

    @DynamoDBIndexRangeKey(attributeName = "mes_movimentacao", globalSecondaryIndexName = "gsi_busca_cliente_por_mes")
    private String mesMovimentacao;

    @DynamoDBIndexRangeKey(attributeName = "data_hora_movimentacao", globalSecondaryIndexName = "gsi_busca_cliente_por_data")
    @DynamoDBTypeConvertedTimestamp(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timeZone = "GMT-3")
    private LocalDateTime dataHoraMovimentacao;

    @DynamoDBAttribute(attributeName = "valor_movimentado")
    private BigDecimal valorMovimentado;

    @DynamoDBAttribute(attributeName = "saldo_cliente")
    private BigDecimal saldoCliente;

}
