import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class HandlingTextsChallengesTests {
    @Test
    void testePalindromo() {
        assertThat(verificaPalindromoStringSolucao1("")).isEqualTo(true);
        assertThat(verificaPalindromoStringSolucao1("a")).isEqualTo(true);
        assertThat(verificaPalindromoStringSolucao1("ab")).isEqualTo(false);
        assertThat(verificaPalindromoStringSolucao1("aba")).isEqualTo(true);
        assertThat(verificaPalindromoStringSolucao1("aabb")).isEqualTo(false);
        assertThat(verificaPalindromoStringSolucao1("arara")).isEqualTo(true);

        assertThat(verificaPalindromoStringSolucao2("")).isEqualTo(true);
        assertThat(verificaPalindromoStringSolucao2("a")).isEqualTo(true);
        assertThat(verificaPalindromoStringSolucao2("ab")).isEqualTo(false);
        assertThat(verificaPalindromoStringSolucao2("arara")).isEqualTo(true);
        assertThat(verificaPalindromoStringSolucao2("11111111")).isEqualTo(true);
        assertThat(verificaPalindromoStringSolucao2("11111112")).isEqualTo(false);
    }

    private boolean verificaPalindromoStringSolucao1(String s) {
        if (s.isEmpty()) return true;

        var i = 0;
        var j = s.length() - 1;

        while (i < j) {
            var chrLeft = s.charAt(i);
            var chrRight = s.charAt(j);

            if (chrLeft != chrRight) return false;

            i++;
            j--;
        }

        return true;
    }

    /*
    * Solução 2 não utilizo loops.
    * */
    private boolean verificaPalindromoStringSolucao2(String s) {
        var mid = s.length() / 2;
        return s
            .substring(mid)
            .endsWith(
                new StringBuilder(
                    s.substring(0, mid)
                ).reverse().toString()
            );
    }
}
