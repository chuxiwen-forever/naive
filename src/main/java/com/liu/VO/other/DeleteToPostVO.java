package com.liu.VO.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Menu 中为了把DeleteMapping 换成 PostMapping
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteToPostVO {
    private Long id;
}
