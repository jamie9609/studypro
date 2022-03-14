package com.jamie.javastudy.designPattern.mediator;

/**
 * @PackageName: com.jamie.javastudy.designPattern.mediator
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/14 2:32 下午
 */
public class Client {

    public static void main(String[] args) {
        UnitedNationsSecurityCouncil unitedNationsSecurityCouncil = new UnitedNationsSecurityCouncil();
        UK uk = new UK(unitedNationsSecurityCouncil);
        USA usa = new USA(unitedNationsSecurityCouncil);
        unitedNationsSecurityCouncil.setUk(uk);
        unitedNationsSecurityCouncil.setUsa(usa);
        uk.declare("我们大英发表声明");
        usa.declare("我们美帝不批准");
    }
}
