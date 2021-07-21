# Java Spring Thymeleaf -> github -> Heroku 前後端整合上架實作流程紀錄


## 本文章會注重在這幾點上 : 
* 用 VScode 裝 Java Spring
* 簡單介紹 Java Spring 的框架
* 簡單介紹  Thymeleaf  的用法
* 用一個簡單的例子(本文用三角形面積計算機為例)
* 從github部屬到 Heroku 

## 成品
*  [三角形面積計算機](https://demogithubjava.herokuapp.com/main)  : 開比較慢稍微等一下
*  [github code](https://github.com/yoyoisaboy/demoheroku_java)  
* 本實作成品 : https://demogithubjava.herokuapp.com/main

## 前言
讓我廢話一下，之所以會想做這個小專案，原因是我想將Java的程式用個介面給老師看，這樣老師就可以不用把我程式碼拿去自己執行，在我架的網站做demo就好。另外我先前都是用python flask 來做到前後端整合的，這次用Java來做，感覺沒那麼陌生。  (一開始是這樣想的.....XD)

那我們就進入主題吧 !!

## 用 VScode 裝 Java Spring

### 安裝
進入 [VScode](https://code.visualstudio.com/) 後 Ctrl + Shift + x 打開 Extensions 安裝我們需要的工具，我們會用到 : 

1. Language Support for Java(TM) by Red Hat
2. Debugger for Java
3. Maven for Java
4. Java Test Runner
5. Project Manager for Java
6. Java Extension Pack
7. Spring Initializr Java Support
8. Spring Boot Tools
9. Spring Boot Dashboard
10. Spring Boot Extension Pack

因為會用到一些網頁，所以我推薦也把這些順便裝起來 (不影響最後成品)
1. HTML CSS Support
2. IntelliSense for CSS class names in HTML
3. Live Server
4. Path Intellisense

---

### 開專案

1. Ctrl + Shift + p 再打 Spring Initializr: Create a Maven Project  
2. Specify Spring Boot version 看你用啥版本，我選 2.5.2
3. 選Java
4. Group Id for your project. 自取，本案例用已取好的 com.example
5. Artifact Id for your project. 同上，demo
6. Specify packaging type. 選 Jar
7. Specify Java version. 看你Java版本
8. Select dependencies to add. 
    * Spring Web
    * Spring Boot DevTools
    * Thymeleaf
9. 選好你要的檔案位置後，右下角點open    
10. 右下角顯示 ... import them? 選 Yes
11. 完成如下
![](https://i.imgur.com/xhgD4nA.png)


:::danger
假如SPRING BOOT DASHBOARD沒東西，就 File -> Close Folder -> Open Folder (重新開啟)
就好了。
:::

---

### 前置作業 ( 我的習慣，看個人~~ )

* 在 "專案資料" 夾中加入 controllers 資料夾，像我是src\main\demo\controllers
* 在 src\main\resources\static 中加 css
* 在 src\main\resources\templates 中加 img

以上是為了方便接下來的介紹做的前置作業，請依照你開發的程度做好規劃。
   
---  
   
## 簡單介紹 Java Spring 的框架

![](https://i.imgur.com/OoPy87n.png)


spring是一個容器框架，用來裝 [javabean](https://www.runoob.com/jsp/jsp-javabean.html)，中間層框架可以連接在一起作用，比如說把[Struts和hibernate](https://www.itread01.com/hkpyhkye.html)結合在一起運用。簡單來說，Spring是一個輕量級的 ["控制反轉"(IoC) 和 "面向切面程式設計"(AOP)](https://codertw.com/程式語言/14083/) 的容器框架。

* [參考來源](https://kknews.cc/code/qb69q4b.html)
* [Java Spring 更詳細的介紹](http://tw.gitbook.net/spring/spring_overview.html)

---

## 簡單介紹  [Thymeleaf](https://www.thymeleaf.org/) 的用法

Thymeleaf 官網是這麼解釋的：

    Thymeleaf is a modern server-side Java template engine for both web and standalone environments.
    
    譯過來就是：Thymeleaf是用於Web和獨立環境的現代伺服器端Java模板引擎。
* [更詳細介紹](https://iter01.com/518210.html)

目前我用到的感覺就是將前端的資料(網頁的 I/O)，利用特殊的符號( @、#、$ ) 完成 [html request](https://developer.mozilla.org/zh-TW/docs/Web/HTTP/Methods)，代替 [JSP](http://tw.gitbook.net/jsp/jsp_quick_guide.html)。我們就做一個三角形計算機來體會一下。

p.s. 接下來的介紹是針對用法，並不會教語法和 OOP~~。

---

## main.html
在 demo\src\main\resources\templates 新增 main.html，完成介面。
```
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <!-- Additional CSS Files -->
  </head>
  <body>
    <tr class="center">
      <td>
        <h1>
          <font style="vertical-align: inherit">
            <font style="vertical-align: inherit">三角形計算器</font>
          </font>
        </h1>
      </td>
    </tr>
    <img
      class="img-float-right lazy-loaded"
      data-src="https://doza.pro/art/math/geometry/img/triangle-area-5.png"
      width="280"
      height="164"
      alt="根據Heron公式的三角形面積"
      src="https://doza.pro/art/math/geometry/img/triangle-area-5.png"
    />
   <br>
   <form  form action="#" th:action="@{/main}" th:object="${triangle_sides}" method="post">
    <input
        class="katex form-control"
        th:field="*{side_a}"
        type="number"
        value=""
        placeholder="輸入值a"
      />
      <input
        class="katex form-control"
        th:field="*{side_b}"
        type="number"
        value=""
        placeholder="輸入值b"
      />
      <input
        class="katex form-control"
        th:field="*{side_c}"
        type="number"
        value=""
        placeholder="輸入值c"
      />
      <input type="submit"/>  
   </form>
   <p th:text="'a =  :' +  ${triangle_sides.side_a}"></p>
   <p th:text="'b =  :' +  ${triangle_sides.side_b}"></p>
   <p th:text="'c =  :' +  ${triangle_sides.side_c}"></p>
   <h2>面積為 : <p th:text=" '' +${area}"></p></h2> 
  </body>
</html>

```
有幾個地方可以注意一下 : 
* 第2行 : <html lang="en" xmlns:th="http://www.thymeleaf.org"> 一定要引入，這樣才能使用 thymeleaf。
* 你可以發現要用符號時，都會用 th: ... 來使用 @{...}、#{...}、${...}。

然後那些符號要怎樣使用呢，先來做個介紹~~

簡單表達式：
* Variable expressions（變量表達式）${...} : 模型屬性（model attributes）

    [OGNL](https://baike.baidu.com/item/OGNL/10365326)，就是屬性的值或是值的一部份。
* Selection expressions（選擇表達式）*{...} : 上下文變量容器

    像是我在 form 裡面有object取叫 ${triangle_sides} ，之後包在 form 裡的 *{side_a}、*{side_b}、*{side_c}，就是 ${triangle_sides} 的物件。你也可以拿掉 form 換打成 $ {triangle_sides.side_a}、$ {triangle_sides.side_b}、$ {triangle_sides.side_c}，相同意思。
* Link (URL) expressions（鏈接表達式）@{...} : 傳URL，這例子是按 sumbit 後 action 到 main 的頁面。
* [更多表達式用法](https://waylau.gitbooks.io/thymeleaf-tutorial/content/docs/standard-expression-syntax.html)

這實作只簡單使用幾個，且用法也很簡單，日後有機會再用其他的方法作介紹。

## application.properties

設定環境，在src\main\resources 的 application.properties 打
```
server.port=8080
spring.thymeleaf.prefix=classpath:/templates/
```
1. 本地端接 8080 port -> localhot:8080
2. 引入 thymeleaf 到 templates

## main_Controller.java 、 Coluction_function.java 、 Htmlrequest_getsides.java

* 在 src\main\demo(你取的專案名稱)\controllers 新增三個.java檔，貼上我的程式碼後會出現紅線的地方，請點它，左邊有燈泡，點燈泡找 import 引入方法。
* 通常用其他類別，檔案名稱第一個字最好大寫，養成習慣，這是淺規則~~
* Controller 是什麼呢? Controller 就類似ATM，它能夠接收前端傳來的資料，也能將資料回應給前端，因為要做這功能所以我習慣檔案名稱會加上Controller。

### 1. main_Controller.java -> 負責啟動網頁，建立 get 和 post ，物件實體化
```
@Controller
public class main_Controller {

    @RequestMapping("/main")
    public String main(){
        return "main";
    }
    @GetMapping("/main")
    public String main(Model model){
        Htmlrequest_getsides triangle_sides = new Htmlrequest_getsides();  //side_a,side_b,side_c
        model.addAttribute("triangle_sides", triangle_sides);
        return "main";
    }

    @PostMapping("/main")
    public String main(@ModelAttribute Htmlrequest_getsides triangle_sides, Model model) {
        
        Coluction_function ans = new Coluction_function();
    
        double side_a = triangle_sides.getSide_a();
        double side_b = triangle_sides.getSide_b();
        double side_c = triangle_sides.getSide_c();
        
        model.addAttribute("triangle_sides", triangle_sides);
        model.addAttribute("area", ans.main(side_a,side_b,side_c));
    
        return "main";
    }
    
}
```
* 函式名稱通常和網站名稱相同，你要改也是可以，目前不影響最後結果。
* @GetMapping : 就是把前端 main.html 傳來的值取得的方法。
* @PostMapping : 就是把後端的值傳到前端 main.html 的方法。
* @ModelAttribute : “Htmlrequest_getsides”的表單可以讓 Spring MVC 通過使用@ModelAttribute  註釋將此對象提供給 Controller 方法。
* addAttribute(x,y) : 取值\傳值，x 是 html th: 那邊你取的名稱，y : 變數值(object或string)。

### 2. Htmlrequest_getsides.java 

定義 get、set 的值，這裡要注意名稱必須符合規則，函式名稱get、set一定小寫，get、set之後第一個字大寫，ex : getSides() ，這樣才符合 request 的規定。

```
public class Htmlrequest_getsides {
    double side_a;
    double side_b;
    double side_c;
      
    public Double getSide_a() { //注意大小寫
        return side_a;
    }
    public void setSide_a(Double side_a) {
        this.side_a = side_a;
    }

    public Double getSide_b() {
        return side_b;
    }
    public void setSide_b(Double side_b) {
        this.side_b = side_b;
    }

    public Double getSide_c() {
        return side_c;
    }
    public void setSide_c(Double side_c) {
        this.side_c = side_c;
    }
}
```
### 3. Coluction_function.java -> 三角形面積算法
```
public class Coluction_function {
    public  String main(double side_a,double  side_b,double  side_c){

        ArrayList<Double> three_sides = new ArrayList<Double>();
        three_sides.add(side_a);
        three_sides.add(side_b);
        three_sides.add(side_c);

        if(check_isTraingle(three_sides)){
           
            return TArea(three_sides).toString();
        }
        else{
            return "NaN";
        }
    }

    //確認是三角形
    public static boolean check_isTraingle(ArrayList<Double> side_map){
        int count  = 0;
        Double perimeter = 0.0 ;
        Collections.sort(side_map);
        for(Double side : side_map){
            if(count==3){
                if(perimeter <= side ) return false;
                perimeter += side;    
            }
            else{
                perimeter += side;
                count+=1;
                }
        }
        return true;
    }
    //算面積
    public static Double TArea(ArrayList<Double> side_map){
        Double perimeter = 0.0;
        for(double number : side_map){ 
            perimeter += number;
        }
        Double s = perimeter/2;
        Double area = Math.sqrt(s*(s-side_map.get(0))*(s-side_map.get(1))*(s-side_map.get(2)) );
        return area;
    }
}
```
---

* 目前檔案有 : 

![](https://i.imgur.com/0YiNA1E.png)

* 然後按左下角的 Dashborad 的 Start

![](https://i.imgur.com/wId1i3b.png)

* Start完後，到瀏覽器打 localhost:8080/main，看看成品

![](https://i.imgur.com/5xRUtzQ.png)


---


## 從github部屬到 [Heroku](https://dashboard.heroku.com/) 

在 pom.xml 裡新增 Maven Compile的設定，這是 heroku 在部屬你的程式時會用到的
```
<build>
	<plugins>
		<plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

* 新增一個 github 的專案，把此程式 push 到 [github](https://github.com/) 上。
* 到 Heroku 申請並開專案。
* Ｃreate new app，取專案名稱。
    ![](https://i.imgur.com/mfgYICp.png)
* Deploy -> Deployment method 選 github。
    ![](https://i.imgur.com/xM8ubWO.png)
* 找你的github上的專案名稱，並搜尋選取Connect。
    ![](https://i.imgur.com/76doLvC.png)
* Automatic deploys 選 Enable Automatic Deploys (她是你github更新時，Heroku也會自動重新佈署)，然後 Deploy Branch 。
    ![](https://i.imgur.com/aFm6kJs.png)
    p.s. 假如有問題看一下log是錯什麼，通常是 java 版本 和 Maven 版本。
* 好了後點 View，進入那個網址然後打我的們網頁進入點，本實作是 main。
    ![](https://i.imgur.com/HCQuz36.png)

* 成品 : https://demogithubjava.herokuapp.com/main



祝你順利（・ω・）~~