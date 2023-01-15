import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
        assertThat("ab" + null + 1).isEqualTo("abnull1");

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
        var s = "animal";

        assertThat(s.indexOf('a')).isEqualTo(0);
        assertThat(s.indexOf("al")).isEqualTo(4);
        assertThat(s.indexOf(0x61, 3)).isEqualTo(4);
        assertThat(s.indexOf("y")).isEqualTo(-1);   // -1 indica indice invalido
        assertThat(s.indexOf("al", 5)).isEqualTo(-1);
    }

    @Test
    void gettingASubstring() {
        var s = "animals";

        assertThat(s.substring(3)).isEqualTo("mals");
        assertThatExceptionOfType(StringIndexOutOfBoundsException.class).isThrownBy(() -> {
            s.substring(8);
        });
        assertThatExceptionOfType(StringIndexOutOfBoundsException.class).isThrownBy(() -> {
            assertThat(s.substring(3, 8)).isEqualTo("mals");
        });
        assertThat(s.substring(3, 3)).isEmpty();
        assertThat(s.substring(3, 4)).isEqualTo("m");
        assertThat(s.substring(6, 7)).isEqualTo("s");
        assertThatExceptionOfType(StringIndexOutOfBoundsException.class).isThrownBy(() -> {
            s.substring(4, 2);
        });
        assertThat(s.substring(s.indexOf('m'))).isEqualTo("mals");
    }

    @Test
    void adjustingCase() {
        var s = "Animals";

        assertThat(s.toUpperCase()).isEqualTo("ANIMALS");
        assertThat(s.toLowerCase()).isEqualTo("animals");
    }

    @Test
    void checkingForEquality() {
        var s1 = "ABC";
        var s2 = "aBC";

        assertThat(s1.equals("abc")).isFalse();
        assertThat(s1.equalsIgnoreCase("abc")).isTrue();
        assertThat(s2.equalsIgnoreCase("ABC")).isTrue();
    }

    @Test
    void searchingForSubstrings() {
        assertThat("abc".startsWith("a")).isTrue();
        assertThat("abc".startsWith("b")).isFalse();
        assertThat("abc".startsWith("ab")).isTrue();
        assertThat("abc".startsWith("abc")).isTrue();
        assertThat("abc".endsWith("c")).isTrue();
        assertThat("abc".endsWith("bc")).isTrue();
        assertThat("abc".endsWith("abc")).isTrue();
        assertThat("abc".contains("b")).isTrue();
        assertThat("abc".contains("bc")).isTrue();
        assertThat("abc".contains("d")).isFalse();
        assertThat("abc".contains("a")).isEqualTo("abc".indexOf("a") != -1);
    }

    @Test
    void replacingValues() {
        var s = "abcabc";
        assertThat(s.replace('b', 'B')).isEqualTo("aBcaBc");
        assertThat(s.replace("ab", "AB")).isEqualTo("ABcABc");
        assertThat(s.replace("d", "AB")).isEqualTo("abcabc");
    }

    @Test
    void removingBlankSpaces() {
        // trim => nao remove caracteres Unicode em branco
        // strip => remove caracteres Unicode em branco
        assertThat(" abc\t ".trim()).isEqualTo("abc");
        assertThat(" abc\t\u2000 ".trim()).isEqualTo("abc\t\u2000").hasSize(5);
        assertThat(" abc\t\u2000 ".strip()).isEqualTo("abc").hasSize(3);
        assertThat(" abc\t ".stripLeading()).isEqualTo("abc\t ").hasSize(5);
        assertThat(" abc\t ".stripTrailing()).isEqualTo(" abc").hasSize(4);
    }

    @Test
    void workingWithIndentationAndNormalization() {
        var block = """
            a
             b
            c""";

        var blockAsCharArray = block.toCharArray();
        var blockCharSeqDebug = "";

        for (var chr : blockAsCharArray) {
            if (chr == '\n') chr = 'N'; // N == newline \n

            blockCharSeqDebug += "[%c]".formatted(chr);
        }

        assertThat(blockCharSeqDebug).isEqualTo("[a][N][ ][b][N][c]");
        assertThat(block.length()).isEqualTo(6);
        assertThat(blockAsCharArray.length).isEqualTo(6);

        block = block.indent(1);
        blockAsCharArray = block.toCharArray();
        blockCharSeqDebug = "";

        for (var chr : blockAsCharArray) {
            if (chr == '\n') chr = 'N'; // N == newline \n

            blockCharSeqDebug += "[%c]".formatted(chr);
        }

        // indent(1) = + 1 whitespace + newline ao final caso não exista. Chamamos de Normalization.
        assertThat(blockCharSeqDebug).isEqualTo("[ ][a][N][ ][ ][b][N][ ][c][N]");
        assertThat(block.length()).isEqualTo(10);

        // indent(-1) = - 1 whitespace + newline ao final caso não exista.
        block = block.indent(-1);
        assertThat(block.length()).isEqualTo(7);

        var concat = " a\n" +
            "  b\n" +
            " c";

        assertThat(concat.stripIndent()).isEqualTo("a\n b\nc");

        /*
         var str =
         ......<html>
         ...........<head></head>
         ......</html>

         se aplicar stripIndent, teremos

         <html>
         .....<head></head>
         </html>

         Observe que stripIndent remove whitespaces preservando a indentação relativa ao conteúdo. Ou seja,
         somente os espaços mais externos à extremidade da linha.

         Muito interessante que a doc oficial relata a definição do que seja considerada uma "linha".
         Ver em https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html#stripIndent()

         stripIndent() = remove indentações mais externas, normaliza linhas, sem colocar newline no final do texto.
        */
    }

    @Test
    void translatingEscapes() {
        var str = "1\\t2\\n";

        assertThat(str).isEqualTo("1\\t2\\n");
        assertThat(str.translateEscapes()).isEqualTo("1\t2\n");
    }

    @Test
    void checkingForEmptyOrBlankStrings() {
        assertThat("   ".isEmpty()).isFalse();
        assertThat("   ".isBlank()).isTrue();
        assertThat("".isEmpty()).isTrue();
        assertThat("".isBlank()).isTrue();
    }

    @Test
    void formattingValues() {
        var greeting = String.format("Hello %s!", "Kate");

        assertThat(greeting).isEqualTo("Hello Kate!");

        greeting = "Hello %s!".formatted("John");

        assertThat(greeting).isEqualTo("Hello John!");
    }

    @Test
    void usingStringBuilderClass() {
        // Código abaixo é muito ineficiente. Teremos 27 instanciações de Strings.
        var alpha = "";

        for (char current = 'a'; current <= 'z'; current++)
            alpha += current;

        assertThat(alpha).isEqualTo("abcdefghijklmnopqrstuvwxyz");

        // Com StringBuilder, nenhum objeto precisa ser criado a cada iteração do loop
        var alphabet = new StringBuilder();

        for (char current = 'a'; current <= 'z'; current++)
            alphabet.append(current);

        assertThat(alphabet.toString()).isEqualTo("abcdefghijklmnopqrstuvwxyz");
    }

    @Test
    void mutabilityAndChaining() {
        // Devido a mutabilidade de StringBuilder, que permite ser mais eficiente do que String,
        // acaba exigindo mais atenção quando se faz "chaining"
        var a = new StringBuilder("abc");
        var b = a.append("de"); // b é referência a 'a'
        b = b.append("f").append("g");

        assertThat(a.toString()).isEqualTo("abcdefg");
        assertThat(b.toString()).isEqualTo("abcdefg");
    }

    @Test
    void creatingAStringBuilder() {
        var sb1 = new StringBuilder();
        var sb2 = new StringBuilder("animal");
        var sb3 = new StringBuilder(10);

        assertThat(sb1).hasSize(0);
        assertThat(sb2).hasSize(6);
        assertThat(sb3).hasSize(0); // capacity é diferente de size (length)
        assertThat(sb3.capacity()).isEqualTo(10); // capacity é uma ideia para que o Java reserve espaço
    }

    @Test
    void usingCommonMethodsWithStringBuilder() {
        var sb = new StringBuilder("animals");
        var sub = sb.substring(sb.indexOf("a"), sb.indexOf("al"));

        assertThat(sb.length()).isEqualTo(7);
        assertThat(sub)
            .isEqualTo("anim")
            .isInstanceOf(String.class); // substring sempre entrega um String, que é seu estado interno, nao StringBuilder
    }

    @Test
    void appendValuesWithStringBuilder() {
        var sb = new StringBuilder();

        sb.append(1).append('-').append("c").append(true);

        assertThat(sb.toString()).isEqualTo("1-ctrue");
    }

    @Test
    void insertingDataWithStringBuilder() {
        var sb = new StringBuilder("animal");

        sb.insert(6, '-'); // animal-
        sb.insert(0, '-'); // -animal-
        sb.insert(4, '-'); // -ani-mal-

        assertThat(sb.toString()).isEqualTo("-ani-mal-");
    }

    @Test
    void deletingContents() {
        final var sb = new StringBuilder("abcdef");

        sb.delete(1, 3);

        assertThat(sb).containsOnlyOnce("adef");

        sb.deleteCharAt(0);

        assertThat(sb).containsOnlyOnce("def");

        // Java dá erro caso 'sb' não seja final para uso num lambda e
        // logo em seguida reatribua a variável com outra instancia de StringBuilder
        assertThatExceptionOfType(StringIndexOutOfBoundsException.class).isThrownBy(() -> sb.deleteCharAt(5));

        // Método delete pode ser mais flexível do que muitos outros
        var newSb = new StringBuilder("abcdef");

        newSb.delete(1, 100);

        assertThat(newSb).containsOnlyOnce("a");
    }

    @Test
    void replacingPortions() {
        var sb = new StringBuilder("pigeon dirty");

        sb.replace(3, 6, "sty");

        assertThat(sb).containsOnlyOnce("pigsty dirty");

        // Primeiro: replace executa um 'delete' nas posições indicadas
        // Segundo: replace em seguida faz 'insert' nas posiçoes indicadas

        sb.replace(3, 100, "");

        assertThat(sb).containsOnlyOnce("pig");

        // Atente-se ao fato de que replace faz um delete lógico primeiramente. Por mais que se coloque posições
        // que extrapolem o tamanho inicial, o delete apenas apaga o que tiver existido até os limites estabelecidos
        // Fique tranquilo, não será colocado empty strings depois com limites que extrapolem o tamanho inicial.
    }

    @Test
    void reversing() {
        var sb = new StringBuilder("hello world!");

        sb.reverse();

        assertThat(sb).containsOnlyOnce("!dlrow olleh");
    }

    @Test
    void comparingEquals() {
        var one = new StringBuilder();
        var two = new StringBuilder();
        var three = one.append("a");

        assertThat(one == two).isFalse();  // references
        assertThat(one == three).isTrue();

        var x = "Hello World";
        var z = "  Hello World".trim();

        assertThat(x.equals(z)).isTrue();

        var name = "a";
        var builder = new StringBuilder("a");

        // Não é possível compilar devido: TIPOS INCOMPATIVEIS.
        // assertThat(name == builder).isTrue();
    }

    @Test
    void stringOrInternPool() {
        var x = "Hello World";
        var y = "Hello World";

        assertThat(x == y).isTrue();

        var t = "Hello World";
        var v = "   Hello World".trim();

        assertThat(t == v).isFalse();

        var singleString = "hello world";
        var concat = "hello ";
        concat += "world";

        assertThat(singleString == concat).isFalse();

        var w = "Hello World";
        var u = new String("Hello World");

        assertThat(w == u).isFalse();

        var name = "Hello World";
        var name2 = new String("Hello World").intern();

        assertThat(name == name2).isTrue();

        var first = "rat" + 1;                                 // compile-time
        var second = "r" + "a" + "t" + 1;                      // compile-time
        var third = "r" + "a" + "t" + new String("1");  // run-time

        assertThat(first == second).isTrue();
        assertThat(first == second.intern()).isTrue();
        assertThat(first == third).isFalse();
        assertThat(first == third.intern()).isTrue();

        var expr1 = String.format("n1 == v1 " + "and n2 == %s", "v2");
        var expr2 = "n1 == v1 and n2 == v2";

        assertThat(expr1 == expr2).isFalse();
    }
}
