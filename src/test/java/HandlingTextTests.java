
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * Manipulate text, including text blocks, using String and StringBuilder classes.
 */
public class HandlingTextTests {
    @Test
    void creatingAndManipulatingStrings() {
        String name = "John"; // name é uma referencia para o objeto String
        String otherName = new String("Mary"); // 'new' é opcional

        name = null;
        assertThat(name).isNull(); // name aponta para null, o que nos permite dizer que name é uma referencia

        // Um text block
        String phraseOfDay = """
            Carpe diem""";

        // String implementam a interface CharSequence
        assertThat(otherName).isInstanceOf(CharSequence.class);
        assertThat(phraseOfDay).isEqualTo("Carpe diem");
    }

    @Test
    void concatenating() {
        // Direção da concatenação: LEFT -> RIGHT
        // Se o elemento mais à esquerda é um numero, a operação é de adição (N +{add} ...)
        // Se o elemento mais à direita é uma String, a operacao é de concatenação (S +{concat} ...)
        // IMPORTANTE: null é tratado como String na concatenação, tanto LEFT quanto RIGHT
        assertThat(1 + 2 + "ab").isEqualTo("3ab");
        assertThat("ab" + 1).isEqualTo("ab1");
        assertThat("ab" + 1 + 2).isEqualTo("ab12");
        assertThat('a' + "b").isEqualTo("ab");
        assertThat("ab" + null).isEqualTo("abnull");
        assertThat(null + "ab").isEqualTo("nullab");

        int three = 3;
        String four = "4";
        assertThat(1 + three + four).isEqualTo("44");
        assertThat(1 + three + four + 2).isEqualTo("442");

        String s = "1";
        s += "2";
        s += 3; // mesmo que s = s + 3, com s sendo string, logo operação é de concatenação
        assertThat(s).isEqualTo("123");
    }

    @Test
    void importantStringMethods_indexing() {
        String animal = "animal";
        char[] animalCharSequence = animal.toCharArray();

        var firstIndex = 0;

        assertThat(animalCharSequence[firstIndex]).isEqualTo('a');
        assertThat(animalCharSequence[1]).isEqualTo('n');
        assertThat(animalCharSequence[2]).isEqualTo('i');
        assertThat(animalCharSequence[3]).isEqualTo('m');
        assertThat(animalCharSequence[4]).isEqualTo('a');
        assertThat(animalCharSequence[5]).isEqualTo('l');
    }

    @Test
    void stringsAreImmutables() {
        String immutable = "a";

        immutable.replace("a", "b");

        assertThat(immutable).isEqualTo("a");

        // String são imutáveis, logo todos seus métodos retorna novos objetos do tipo String
        String newImmutable = immutable.replace("a", "b");
        assertThat(newImmutable).isEqualTo("b");
    }

    @Test
    void getStringLength() {
        var phrase = "Carpe diem";
        var phraseLength = phrase.length();

        assertThat(phraseLength).isEqualTo(10);
    }

    @Test
    void getASingleCharacter() {
        var name = "animals";
        var charAtPositionZero = name.charAt(0);
        var charAtPositionFive = name.charAt(5);

        assertThat(charAtPositionZero).isEqualTo('a');
        assertThat(charAtPositionFive).isEqualTo('l');

        assertThatExceptionOfType(StringIndexOutOfBoundsException.class).isThrownBy(() -> name.charAt(7));
    }

    @Test
    void findingAnIndex() {
        // TODO
    }

    @Test
    void gettingASubstring() {
        // TODO
    }

    @Test
    void adjustingCase() {
        // TODO
    }
}
