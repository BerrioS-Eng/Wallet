package com.project.wallet.config;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuracion del feed de mercado de Binance.
 *
 * <p>La URL del combined stream se construye aqui y en ningun otro sitio: si
 * manana cambian los simbolos o el host, solo se toca el application.yml.
 */
@ConfigurationProperties(prefix = "binance")
public record BinanceProperties(String wsBaseUrl, List<String> symbols) {

    /**
     * wss://host/stream?streams=btcusdt@trade/ethusdt@trade
     *
     * <p>Binance exige los nombres de stream en minuscula; los simbolos se
     * declaran en mayuscula por legibilidad y se normalizan aqui.
     */
    public String combinedStreamUrl() {
        String streams = symbols.stream()
                .map(symbol -> symbol.toLowerCase(Locale.ROOT) + "@trade")
                .collect(Collectors.joining("/"));
        return wsBaseUrl + "?streams=" + streams;
    }
}
