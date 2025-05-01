RESTful API는 REST(Representational State Transfer) 원칙을 따르는 웹 API를 의미해요. 주요 특징은 다음과 같습니다:

자원 기반:
URI(Uniform Resource Identifier)를 사용하여 데이터나 기능(예: 사용자, 게시글 등)을 자원(Resource)으로 식별합니다.

HTTP 메서드 활용:
각 메서드는 자원에 대해 특정 작업을 의미합니다.

GET: 자원의 조회

POST: 새로운 자원의 생성

PUT/PATCH: 기존 자원의 수정

DELETE: 자원의 삭제

1. POST – 자원 생성
   목적: 클라이언트에서 전송된 데이터를 기반으로 새로운 자원을 생성합니다.

필요 애노테이션:

@PostMapping: POST 요청을 매핑

@RequestBody: 요청 본문을 자바 객체로 변환

예시 코드:

java
복사
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    // 생성자 주입 등으로 articleService를 초기화

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        Article createdArticle = articleService.createArticle(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle);
    }
}
설명: 클라이언트가 POST 요청으로 전송한 JSON 데이터를 @RequestBody가 Article 객체로 변환한 후, 서비스 계층에서 생성 작업을 수행하고, 생성된 Article과 함께 HTTP 201(Created) 상태를 반환합니다.

2. GET – 단일 자원 조회
   목적: 특정 ID에 해당하는 자원을 조회합니다.

필요 애노테이션:

@GetMapping: GET 요청을 매핑

@PathVariable: URL 경로에 포함된 변수 추출

예시 코드:

java
복사
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable Long id) {
        Article article = articleService.getArticle(id);
        return ResponseEntity.ok(article);
    }
}
설명: URL 경로의 {id} 값을 @PathVariable로 받아서 해당 ID의 Article을 조회한 후 HTTP 200(OK) 상태와 함께 반환합니다.

3. GET – 자원 목록 조회
   목적: 전체 자원 리스트를 조회합니다.

필요 애노테이션:

@GetMapping: GET 요청을 매핑

예시 코드:

java
복사
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }
}
설명: 전체 Article 목록을 조회하여 HTTP 200 상태와 함께 반환합니다.

4. PUT – 전체 자원 업데이트
   목적: 기존 자원의 모든 필드를 클라이언트가 전송한 데이터로 교체합니다.

필요 애노테이션:

@PutMapping: PUT 요청을 매핑

@PathVariable: URL 경로 변수

@RequestBody: 요청 본문을 자바 객체로 변환

예시 코드:

java
복사
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        Article updatedArticle = articleService.updateArticle(id, article);
        return ResponseEntity.ok(updatedArticle);
    }
}
설명: URL의 {id}로 조회한 Article을 클라이언트가 제공한 새 데이터로 전체 업데이트하며, 업데이트된 Article 객체와 함께 HTTP 200 상태를 반환합니다.

5. PATCH – 부분 자원 업데이트 (선택 사항)
   목적: 기존 자원의 일부 필드만 업데이트할 때 사용합니다.

필요 애노테이션:

@PatchMapping: PATCH 요청을 매핑

@PathVariable: URL 경로 변수

@RequestBody: 요청 본문을 자바 객체나 Map으로 변환

예시 코드:

java
복사
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PatchMapping("/{id}")
    public ResponseEntity<Article> partialUpdateArticle(@PathVariable Long id,
                                                        @RequestBody Map<String, Object> updates) {
        Article updatedArticle = articleService.partialUpdateArticle(id, updates);
        return ResponseEntity.ok(updatedArticle);
    }
}
설명: 클라이언트가 전달한 일부 필드만 변경하는 요청을 받아, 해당 Article의 일부 값만 업데이트 후 반환합니다.

6. DELETE – 자원 삭제
   목적: 특정 자원을 삭제합니다.

필요 애노테이션:

@DeleteMapping: DELETE 요청을 매핑

@PathVariable: URL 경로 변수 추출

예시 코드:

java
복사
@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final ArticleService articleService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}
설명: URL 경로에 있는 {id}를 통해 특정 Article을 삭제하고, 성공 시 HTTP 204(No Content) 상태를 반환하여 삭제 완료를 알립니다.

부가 사항
클래스 레벨 애노테이션

@RestController: 스프링에게 해당 클래스가 REST API의 컨트롤러임을 알려주며, 반환 값이 JSON으로 자동 변환됩니다.

@RequestMapping: 클래스 레벨에서 기본 URL 경로를 설정할 수 있습니다.

Service, Repository 계층에도 각각

@Service: 서비스 계층 클래스에 사용

@Repository: 데이터 접근 계층 클래스에 사용