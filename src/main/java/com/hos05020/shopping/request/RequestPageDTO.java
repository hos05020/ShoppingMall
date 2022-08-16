package com.hos05020.shopping.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RequestPageDTO {

    private int page;
    private int size;

    public int getOffset(){
        return page * size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
}
