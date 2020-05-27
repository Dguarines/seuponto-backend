package br.com.r4s.adm.arq.utils;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

/**
 * ValidacaoUtils
 */
@Component
@NoArgsConstructor
public class ValidacaoUtils {

    public boolean verificaAtributosNulos(Object instance, String... fieldNames) {
        return Arrays.asList(fieldNames).stream().allMatch(field -> {
            try {
                return Objects.isNull(instance.getClass().getDeclaredField((String) field).get(instance));
            } catch (IllegalAccessException | NoSuchFieldException e) {
                return true;
            }
        });
    }

}