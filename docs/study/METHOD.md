<h1>공부, 기억할 메서드</h1>

String, StringBuilder, wrapper class, Collection, Collections, Arrays 관련 메소드 공부

<String 클래스>

String str;

char[] arr = str.toCharArray();	>> char 배열로 나눠줌

str.toUpperCase();	대문자로 변환
str.toLowerCase();	소문자로 변환

String.valueOf(값);	값을 String 형태로 변환

str.contains("hi");	str에 "hi" 가 포함되었는가

str = "hello";
String[] arr = str.split("")		str 한글자 한글자 나눠서 배열로
arr	h e l l o
idx	0 1 2 3 4
String[] split(String regex) >> regex 기준으로 나눈다


str.charAt(0);	>> 해당 위치의 문자를 반환

boolean startsWith(String prefix)	>> 지정된 문자열(prefix)로 시작되는지 반환
boolean endsWith(String suffix)	>> 지정된 문자열(suffix)로 끝나는지 반환


String replace(CharSequence old, CharSequence new)	>> 해당 문자열의 모든 old -> new 로 바꾼 결과 반환
String replaceAll(String regex, String replacement) >> replace와 동일하게 사용가능, 정규식을 이용하여 변경 가능
str.replaceAll("[abc]", "왕");

replace는 고정된 문자열이 대상, replaceAll은 정규식 대상



String str = "hiefawelilasfdasli";
str.indexOf("li");	>> 7

str = "hi";
str.lastIndexOf("a"); >> -1
str.lastIndexOf("i"); >> 1
뒤에서부터 해당 문자열 위치 찾음
못 찾으면

str.indexOf("i");
str.indexOf("hi")
앞에서부터 해당 문자열 위치 찾음
못 찾으면





String str = "hello";
str.substring(str.length());	>> 빈 문자열 반환

str.substring(2, 5);	>> 2 ~ 4

String substring(int begin)	>> begin 부터 끝까지
String substring(int begin, int end)	>> begin <=  < end



====================================================================================


<StringBuilder>
StringBulider sb = new StringBuilder();
sb.reverse();

char charAt(int index)
StringBuilder append(char[] str)	>> 매개변수로 boolean, char, char[], double, float, int, long, Object, String 가능
StringBuilder delete(int start, int end)	>> start <=  < end 문자열 삭제
StringBuilder deleteCharAt(int index)
StringBuilder insert(int pos, 넣을 값)	>> pos 위치에 삽입
int length()
StringBuilder replace(int start, int end, String str)	>> start <=  < end 사이의 문자열을 str로 교체
void setCharAt(int index, char ch)	>> index 위치의 문자를 ch로 교체
String substring(int start)
String substring(int start, int end)


====================================================================================


<Math 클래스>

반올림
int n = 3.141592;
Math.round(n)	>> 3  Math.round(double) : long 형, Math.round(float) : int형

Math.round((n * 100) / 100.0)	>> 3.14	>> 소수점 두번째 자리까지 반올림

숫자 * 10^원하는 자리 수 / 10^원하는 자리수 >> (n * 1000) / 1000.0 : 3째자리 까지 반올림

Math.ceil() >> 올림
Math.floor() >> 버림


double Math.sqrt(double n)

Math.sqrt(2) = 1.4142135623730951
(int) Math.sqrt(2) = 1

double Math.pow(x, n) >> xⁿ
(int) Math.pow(10, 4) >> 10⁴ = 10000



int n = 210;
(int)Math.log10(n) + 1 : 자릿수 계산
>> 3


====================================================================================

<Map 클래스>
Map<String, Integer> map = new HashMap<>();
for (String player : participant) map.put(player, map.getOrDefault(player, 0) + 1);

getOrDefault(key, 기본값) : key 로 map 에서 조회, 만약 조회결과가 null이면 0 반환


====================================================================================

<List 클래스>
List
add(삽입위치, 삽입할 값)

List<Integer> list = new ArrayList<>();
Collections.swap(list, i, j);	>> list의 i, j 위치값 변경



<PriorityQueue>
큰 정수 먼저 뽑기
PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));












