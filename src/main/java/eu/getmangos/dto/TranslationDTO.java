package eu.getmangos.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode
public class TranslationDTO {

    @Schema(description = "The locale for which this translation applies")
    private Locale locale;

    @Schema(description = "The translated text")
    private String translation;

}
