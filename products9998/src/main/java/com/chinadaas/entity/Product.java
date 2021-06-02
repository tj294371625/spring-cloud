package com.chinadaas.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*******************************************************************************
 * - Copyright (c)  2021  chinadaas.com
 * - File Name: Product
 * - @author: liubc - Initial implementation
 * - Description:
 *
 * - Function List:
 *
 * - History:
 * Date         Author          Modification
 * 2021/6/2      liubc           Create the current class
 *******************************************************************************/
@Setter
@Getter
@ToString
public class Product {

    private String id;

    private String name;

    private double price;
}
