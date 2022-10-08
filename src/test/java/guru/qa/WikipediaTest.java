package guru.qa;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.*;

public class WikipediaTest {

    @BeforeAll
    static void configure(){
        baseUrl = "https://ru.wikipedia.org/";
        browserSize = "1920x1080";
    }

    @ValueSource(strings = {"Test", "Bread"})
    @DisplayName("Проверка корректной выдачи результатов при поиске в Википедии")
    @ParameterizedTest
    void checkCorrectSearchWikipediaWithValueSource(String testData){
        open(baseUrl);
        $("#searchInput").setValue(testData).pressEnter();
        $("#firstHeading").shouldHave(text(testData));

    }


    @EnumSource(Lang.class)
    @DisplayName("Проверка корректной выдачи результатов при поиске в Википедии")
    @ParameterizedTest
    void checkCorrectSearchWikipediaUsingEnumClass(Lang lang){
        open(baseUrl);
        $("#searchInput").setValue(lang.name()).pressEnter();
        $("#firstHeading").shouldHave(text(lang.name()));

    }

    static Stream<Arguments> checkCorrectSearchWikipediaWithMethodSource() {
        return Stream.of(
                Arguments.of(Lang.Test),
                Arguments.of(Lang.Bread)
        );
    }

    @MethodSource("checkCorrectSearchWikipediaWithMethodSource")
    @DisplayName("Проверка корректной выдачи результатов при поиске в Википедии")
    @ParameterizedTest
    void checkCorrectSearchWikipediaUseMethodSource(Lang lang){
        open(baseUrl);
        $("#searchInput").setValue(lang.name()).pressEnter();
        $("#firstHeading").shouldHave(text(lang.name()));

    }
}
