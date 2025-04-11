# 📑전자결재 시스템 (Electronic Approval System)
### 🚀프로젝트 개요
---
이 프로젝트는 기존 C# 기반으로 구현된 전자결재 시스템을 **Java(Spring Boot)** 로 재구현하면서  
**Java, Spring 프레임워크의 구조적 이해와 설계 능력 향상**을 목표로 진행되었습니다.  
Vue.js, Spring Boot, MySQL을 기반으로 설계 및 구현하였고,  
복잡한 결재 프로세스를 관리하는 로직을 중심으로 개발 경험을 쌓았습니다.


### ⭐사용 Stack
---
- **Frontend** : `Vue.js`  
- **Backend** : `Java`, `Spring Boot`  
- **Database** : `MySQL`

### 🎯전자결재 프로세스 흐름
---
- 사용자 로그인
- 결재함에서 문서 목록 조회
- 문서 선택 시 상세 팝업 → 결재 또는 반려 진행
- 양식함에서 문서 양식을 선택하여 작성 → 기안 제출


![Image](https://github.com/user-attachments/assets/4c15e5d8-b319-4f91-ad74-dbd0b5a5612c)   


### 🤔 고민했던 문제 & 해결 전략
---
 ### ✅ 1. 병렬 결재 처리  
- 같은 단계(예: 합의)의 결재자들은 동일한 `approvalOrder`를 부여  
- 해당 단계의 **모든 결재자가 승인해야 다음 단계로 이동**  

### ✅ 2. 동일한 프로세스를 가진 문서 양식 관리 방식  
- 기능은 유사하지만 문서 양식마다 필드가 달라 **개별 클래스로 설계**  
- 공통 기능은 `Interface`로 강제하고, 각 문서가 구현하도록 분리 설계  
- 확장성과 유지보수성을 모두 고려한 구조

### ✅ 3. 인증 상태 유지  
- 로그인 후 사용자 정보를 세션과 쿠키에 저장  
- 프론트(Vue)에서 `withCredentials: true` 옵션을 설정하여 쿠키 전송  
- Spring에서 `Access-Control-Allow-Credentials`와 함께 CORS 설정 처리

### ✅ 4. CORS 정책 설정  
- 개별 컨트롤러에 `@CrossOrigin` 설정 대신, `WebMvcConfigurer`의 `addCorsMappings()`를 사용하여 **전역 CORS 정책 설정**

  ---

### 🌱리팩토링
---
| No | AS-IS | TO-BE | 키워드 | 날짜 |
|----|-------|-------|--------|------|
| 1 | 각 문서 양식을 개별 클래스로만 관리 | 공통 인터페이스 기반으로 통합 | `Interface`, `추상화` | 2025.04.09 |
| 2 | 문서 리스트 기능 분산 처리 | 공통 인터페이스 기반으로 리스트 통합 | `Interface`, `추상화` | 2025.04.09 |
| 3 | 문서별 다른 RequestDto 사용 | 공통 `DraftDto`로 통합 | `공통화`, `간결성` | 2025.04.09 |
| 4 | `switch`문으로 Service 호출 | `Map<Enum, Service>` 구조로 리팩터링 | `Enum`, `Map` `OCP원칙`| 2025.04.09 |
| 5 | `@CrossOrigin`을 Controller마다 설정 | `WebMvcConfigurer`로 전역 CORS 설정 | `CORS`, `전역화` | 2025.04.10 |
| 6 | 리스트 타입을 문자열로 전달 | `EListType` enum 도입으로 실수 방지 | `Enum`, `Type Safety` | 2025.04.10 |
| 7 | 공통 List DTO 사용 | 각 리스트별 전용 DTO 분리 | `DTO`, `확장성`, `UI 유연성` | 2025.04.11 |
||||||
---
### 🔧 서비스 라우팅 방식 개선 (switch → Map)

문서 양식이 추가되거나 변경될 때마다 switch문을 수정해야 하는 단점이 있습니다.
이는 **OCP(Open-Closed Principle)** 에 위배되며, 서비스 확장에 불리한 구조입니다.

이를 개선하기 위해 Map<EFormType, IDocumentService> 형태의 전략 패턴을 적용하여,
서비스를 동적으로 매핑하고, formType을 기준으로 서비스 인스턴스를 자동 선택하도록 구조를 개선했습니다.

`DocumentController`
```java
// AS-IS
 switch (FOID) {
  case "A0AF0E1":
      return expenseDocService.getDocument(docId, FOID);
  case "6B1E74F":
      return vacationDocService.getDocument(docId, FOID);
                      ...
  }

// TO-BE
return documentServiceFactory.getService(request.getFormType())
                 .getDocument(request.getDocId());
```

`documentServiceFactory`
```java
// TO-BE
public DocumentServiceFactory(List<IDocumentService> services) {
    this.serviceMap = services.stream()
            .collect(Collectors.toMap(
                    IDocumentService::getFormType,
                    s -> s
            ));
}
 
public IDocumentService getService(EFormType formType) {
    return serviceMap.get(formType);
}
```


---
## 📚 배운 점

- 복잡한 결재 프로세스를 **상태 기반, 역할 기반으로 나누어 설계하며 설계력과 추상화 능력**을 배웠습니다.
- 인터페이스, DTO 분리, enum 사용 등 **유지보수와 확장성을 고려한 객체 지향적인 설계 방식**을 체득했습니다.
- 쿠키 인증, CORS, JSON 직렬화 등 **프론트-백엔드 간 통신에서 발생할 수 있는 실전 문제들을 직접 경험하고 해결**해 보며 실력을 키웠습니다.

---




