package com.ziyi.pojo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 18:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean <T> {

    private Long total;

    private List<T> items;
}
