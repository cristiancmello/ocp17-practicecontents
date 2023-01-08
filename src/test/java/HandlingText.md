
# Handling Text

## String Methods

* `"hello" instanceof CharSequence == true`
* Class String implements `CharSequence` interface

| Purpose                       | Code                                                                                                                                                                                                  |
|-------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Creating and Handling Strings | `new String()`, `s = "hello"`                                                                                                                                                                         |
| Concatenation                 | `("hello" + 1 + 2).equals("hello12")`, `(1 + 2 + "hello").equals("3hello")`                                                                                                                           |
| String Character Indexing     | `"abc".charAt(0) == 'a'`, `"abc".charAt(4) throws StringIndexOutOfBoundsException`                                                                                                                    |
| Length                        | `"abc".length() == 3`                                                                                                                                                                                 |
| Finding an Index              | `"abc".indexOf("bc") == 1`, `"abc".indexOf('x') == -1`, `"abc".indexOf('c', 2) == 2`, `"abc".indexOf('c', 3) == -1`                                                                                   |
| Getting a Substring           | `"animals".substring(0, 2).equals("an")`, `.substring(1, 1).equals("")`, `.substring(3).equals("mals")`, `.substring(3, 7).equals("mals")`, `.substring(3, 8) throws StringIndexOutOfBoundsException` |
| Adjusting Case                | `"abc".toUpperCase().equals("ABC")`, `"ABC".toLowerCase().equals("abc")`                                                                                                                              |
| Checking Equality             | `"abC".equals("abc") == false`, `"aBC".equalsIgnoreCase("abc") == true`                                                                                                                               |
| Searching for Substrings      | `"hello".startsWith("he") == true`, `.endsWith("lo") == true`, `.contains("ll") == true`                                                                                                              |
| Replacing values              | `"hello".replace('h', 'H').equals("Hello")`, `.replace("hello", "holá").equals("holá")`                                                                                                               |
| Removing Blank Spaces         | `" \tabc\u2000".trim().equals("abc\u2000")`, `.strip().equals("abc")`, `.stripLeading().equals("abc\u2000")`, `.stripTrailing().equals(" \tabc")`                                                     |

