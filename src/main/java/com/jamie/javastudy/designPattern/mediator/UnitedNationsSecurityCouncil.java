package com.jamie.javastudy.designPattern.mediator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName: com.jamie.javastudy.designPattern.mediator
 * @ClassName: UnitedNationsSecurityCouncil
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/14 2:27 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnitedNationsSecurityCouncil extends UnitedNations{
    private USA usa;
    private UK uk;

    @Override
    public void declare(String message, Country country) {
        if(country == usa) {
            uk.getMessage(message);
        }else if (country == uk) {
            usa.getMessage(message);
        }
    }
}
