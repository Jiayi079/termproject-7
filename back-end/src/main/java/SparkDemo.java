import com.google.gson.Gson;
import static spark.Spark.*;

class InputDto{
  String firstName;
  String lastName;
}

class OutputDto{
  String message;

  public OutputDto(String message){
    this.message = message;
  }
}

public class SparkDemo {

  public static void main(String[] args) {
    port(1234);
    Gson gson = new Gson();

    get("/hello", (req, res) -> "asd");

    get("/",(req, res) -> "This is the root path");

    // group relate endpoints
    path("/api",() -> {
      get("/a",(req, res) -> "A"); // /api/a
      get("/b",(req, res) -> "B"); // /api/b

      //queryParams part of query string (GET)
      get("/queryTest", (req,res)->{
        String name = req.queryParams("name");
        if(name == null || name.isEmpty()){
          return "Hello, please enter your name!";
        }
        return "Hello " + name + "!";
      });

      //body (POST or PUT)
      post("/postTest", (req, res)->{
        String body = req.body(); // raw text in json format
        var inputDto = gson.fromJson(body, InputDto.class);
        String message = "Hello "+ inputDto.firstName + " " + inputDto.lastName;
        System.out.println(body);
        System.out.println(message);
        var outputDto = new OutputDto(message);
        return gson.toJson(outputDto); //java object -> json String
      });
    });
  }
}
