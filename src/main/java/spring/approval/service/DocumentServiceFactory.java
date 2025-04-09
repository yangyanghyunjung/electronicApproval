package spring.approval.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import spring.approval.domain.EFormType;
import spring.approval.service.documents.IDocumentService;

@Service
public class DocumentServiceFactory {
    private final Map<EFormType, IDocumentService> serviceMap;

    // 여기 다시 보기
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
}