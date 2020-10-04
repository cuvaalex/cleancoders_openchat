package org.openchat.domain.post;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class LanguageServiceShould {

    private LanguageService languageService = new LanguageService();

    @Test
    @Parameters({
            "elephant",
            "ELEPHANT",
            "ElePhanT",
            "Grey Elephant",
            "ORANGE",
            "OranGe",
            "Big Orange juice",
            "A sweet Ice Cream"
    })
    public void inform_when_text_contain_inappropriate_language(String text) {
        assertThat(languageService.isInappropriate(text)).isTrue();
    }

    @Test
    public void inform_when_text_doesnt_contain_inappropriate_language() {
        assertThat(languageService.isInappropriate("house")).isFalse();
    }
}
