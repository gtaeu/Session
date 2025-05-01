클래스 구성

ArticleController 클래스: REST API의 진입점 역할을 하며, 클라이언트의 요청을 받아 ArticleService에 위임합니다.

ArticleService 주입: private final ArticleService articleService;를 통해 서비스 계층의 의존성을 주입받습니다. (보통 생성자 주입을 통해 초기화)

메서드 별 설명

createArticle 메서드

어노테이션: @PostMapping("/api/v1/articles")
→ HTTP POST 요청을 /api/v1/articles 경로로 받습니다.

매개변수: @RequestBody Article article
→ 클라이언트로부터 JSON 형식의 Article 데이터를 받아 Article 객체로 변환합니다.

동작: articleService.createArticle(article)를 호출하여 Article을 생성하고, 생성된 Article의 ID를 반환합니다.

응답: HTTP 상태 코드 201(Created)와 함께 생성된 Article의 ID를 포함하는 ResponseEntity를 반환합니다.

getArticle 메서드

어노테이션: @GetMapping("/api/v1/article/{articleId}")
→ HTTP GET 요청을 /api/v1/article/{articleId} 경로로 받으며, URL 경로의 일부인 articleId를 동적으로 받습니다.

매개변수: @PathVariable Long articleId
→ URL에 포함된 articleId 값을 메서드 매개변수에 바인딩합니다.

동작: articleService.findById(articleId)를 호출하여 해당 ID의 Article을 조회합니다.

응답: HTTP 200(OK) 상태와 함께 조회된 Article 객체를 반환합니다.

getAllArticles 메서드

어노테이션: @GetMapping("/api/v1/articles")
→ HTTP GET 요청을 /api/v1/articles 경로로 받습니다.

동작: articleService.findAll()을 호출하여 전체 Article 목록을 조회합니다.

응답: HTTP 200(OK) 상태와 함께 Article 리스트를 반환합니다.

searchArticles 메서드

어노테이션: @GetMapping("/api/v1/articles/search")
→ HTTP GET 요청을 /api/v1/articles/search 경로로 받습니다.

매개변수: @RequestParam String title
→ URL 쿼리 파라미터로 전달된 title 값을 메서드 매개변수에 바인딩합니다.

동작: articleService.findByTitle(title)를 호출하여 해당 제목과 관련된 Article들을 검색합니다.

응답: HTTP 200(OK) 상태와 함께 검색 결과로 나온 Article 리스트를 반환합니다.