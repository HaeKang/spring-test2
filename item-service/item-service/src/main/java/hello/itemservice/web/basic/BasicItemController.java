package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemReposirory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemReposirory itemReposirory;

    // LOMBOK의 RequiredArgsConstructor를 통해 final이 붙은 변수의 생성자 자동생성성
//   @Autowired
//    public BasicItemController(ItemReposirory itemReposirory){
//        this.itemReposirory = itemReposirory;
//    }


    @GetMapping
    public String items(Model model){
        List<Item> items = itemReposirory.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = itemReposirory.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    // 상품등록 V1
//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model){

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemReposirory.save(item);

        model.addAttribute("item", item);

        return "basic/items";
    }

    // 상품등록 v2
    // @ModelAttribute 에서 괄호에 ("item") 이게 이 코드 역할을 해주는 것, 자동추가해줌!
    //    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item){

        itemReposirory.save(item);
//        model.addAttribute("item", item); // @ModelAttribute 에서 괄호에 ("item") 이게 이 코드 역할을 해주는 것, 자동추가해줌!

        return "basic/items";
    }

    // 상품등록 v3
    // @ModelAttribute 에서 이름을 삭제하면 ("이파트") 클래스명을 이름으로 자동생성
    // 이때 클래스명에서 첫글자를 소문자로 바꿔서 진행
    // ex) @ModelAttribute Hellodata => model.addAttribute("hellodata", hellodata); 이런식으로
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item){

        itemReposirory.save(item);

        return "basic/items";
    }

    // 상품등록 v4
    // @ModelAttribute 생략 가능
//    @PostMapping("/add")
    public String addItemV4(Item item){

        itemReposirory.save(item);

        return "basic/items";
    }

    // PRG POST/REDIRECT/GET 으로 새로고침 문제 해결
//    @PostMapping("/add")
    public String addItemV5(Item item){

        itemReposirory.save(item);

        return "redirect:/basic/items/" + item.getId() ;
    }

    // RedirectAttributes 사용
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes){

        Item savedItem = itemReposirory.save(item);

        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemReposirory.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item){
        itemReposirory.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }



    // 테스트용 데이터 추가
    @PostConstruct
    public void init(){
        itemReposirory.save(new Item("itemA", 10000, 10));
        itemReposirory.save(new Item("itemB", 20000, 20));
    }

}
