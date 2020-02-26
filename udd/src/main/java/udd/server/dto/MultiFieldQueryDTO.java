package udd.server.dto;

import java.util.List;

public class MultiFieldQueryDTO {
    private String tip;
    private List<QueryDTO> queryDTOList;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public MultiFieldQueryDTO(String tip, List<QueryDTO> queryDTOList) {
        this.tip = tip;
        this.queryDTOList = queryDTOList;
    }

    public List<QueryDTO> getQueryDTOList() {
        return queryDTOList;
    }

    public void setQueryDTOList(List<QueryDTO> queryDTOList) {
        this.queryDTOList = queryDTOList;
    }

    public MultiFieldQueryDTO() {
    }
}
