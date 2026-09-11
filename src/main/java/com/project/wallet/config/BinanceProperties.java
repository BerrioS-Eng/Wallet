package com.project.wallet.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/*
*  <p> Aqui se arma URL de combined stream: si mañana cambian los simbolos o host, solo se toca el application.yml
*/
@ConfigurationProperties(prefix = "binance")
public record BinanceProperties(String wsBaseUrl, List<String> symbols) {
    /*
    * wss://host/stream?streams=btcusdt@trade/ethusdt@trade
    * Se normalizan los nombres de stream en minuscula.
    * */
    public String combinedStreamUrl() {
        String streams = symbols.stream()
                .map(symbol -> symbol.toLowerCase(Locale.ROOT) + "@trade")
                .collect(Collectors.joining("/"));
        return wsBaseUrl + "?streams=" + streams;
    }
}
