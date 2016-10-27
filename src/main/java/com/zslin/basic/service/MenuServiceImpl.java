package com.zslin.basic.service;

import com.zslin.basic.dto.MenuDto;
import com.zslin.basic.dto.PMenuDto;
import com.zslin.basic.model.Menu;
import com.zslin.basic.tools.PinyinToolkit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zsl-pc on 2016/9/2.
 */
@Component
@Transactional
public class MenuServiceImpl {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private IMenuService menuService;

    public List<Menu> findByPid(Integer pid, String type) {
        String hql = "FROM Menu m WHERE 1=1 ";
        if(pid!=null && pid>0) {hql += " AND m.pid="+pid;}
        else {hql += " AND m.pid is null ";}
        if(type!=null && ("1".equals(type) || "2".equals(type))) {
            hql += " AND m.type='"+type+"'";
        }
        hql += " ORDER BY m.orderNum ASC";
        Query q = em.createQuery(hql);
        return q.getResultList();
    }

    public List<PMenuDto> queryMenuDto(Integer userId) {
        List<PMenuDto> res = new ArrayList<>();
        List<Menu> menuList = menuService.findByUser(userId);
        for(Menu m : menuList) {
            if(m.getPsn()==null || "root".equalsIgnoreCase(m.getPsn())) { //说明是一级菜单
                PMenuDto ppmDto = new PMenuDto(m.getId(), m, new ArrayList<>());
                if(!res.contains(ppmDto)) {res.add(ppmDto);}
            } else if(!"root".equalsIgnoreCase(m.getPsn()) && m.getSn().indexOf(".")<0) { //说明是二级菜单
                Menu pm = menuService.findBySn(m.getPsn());
                PMenuDto ppmDto = new PMenuDto(pm.getId(), pm, new ArrayList<>());
                if(!res.contains(pm)) {
                    res.add(ppmDto);
                }
                res.get(res.indexOf(ppmDto)).getChildren().add(new MenuDto(m.getId(), m, new ArrayList<>()));
            } else if(m.getSn().indexOf(".")>0) { //说明是三级菜单
                Menu pm = menuService.findBySn(m.getPsn()); //这是二级菜单
                MenuDto pmDto = new MenuDto(pm.getId(), pm, new ArrayList<>());
                Menu ppm = menuService.findBySn(pm.getPsn()); //这是一级菜单
                PMenuDto ppmDto = new PMenuDto(ppm.getId(), ppm, new ArrayList<>());
                if(!res.contains(ppmDto)) { //如果不包含一级菜单
                    res.add(new PMenuDto(ppm.getId(), ppm, new ArrayList<>()));
                }
                if(!res.get(res.indexOf(ppmDto)).getChildren().contains(pmDto)) { //如果不包含二级菜单
                    res.get(res.indexOf(ppmDto)).getChildren().add(pmDto);
                }
                res.get(res.indexOf(ppmDto)).getChildren().get(res.get(res.indexOf(ppmDto)).getChildren().indexOf(pmDto)).getChildren().add(m);
            }
        }
        for(PMenuDto ppm : res) {
            Collections.sort(ppm.getChildren());
        }
        Collections.sort(res);
        return res;
    }

    public List<MenuDto> queryMenuDtoNew(Integer userId) {
        List<MenuDto> res = new ArrayList<>();
        List<Menu> menuList = menuService.findByUser(userId);
        for(Menu m : menuList) {
            if(m.getPsn()==null || "root".equalsIgnoreCase(m.getPsn())) { //说明是一级菜单
                MenuDto pmDto = new MenuDto(m.getId(), m, new ArrayList<>());
                if(!res.contains(pmDto)) {res.add(pmDto);}
            } else if(!"root".equalsIgnoreCase(m.getPsn())) { //说明是二级菜单
                Menu pm = menuService.findBySn(m.getPsn());
                MenuDto pmDto = new MenuDto(pm.getId(), pm, new ArrayList<>());
                if(!res.contains(pmDto)) {
                    res.add(pmDto);
                }
                res.get(res.indexOf(pmDto)).getChildren().add(m);
            }
        }
        /*for(MenuDto pm : res) {
            Collections.sort(pm.getChildren());
        }*/
        Collections.sort(res);
        return res;
    }

    public void addOrUpdate(Menu menu) {
        Menu m = menuService.findBySn(menu.getSn());
        try {
            String prSn = menu.getPsn();
            if(prSn!=null && !"".equals(prSn)) {
                boolean isEnglish = prSn.getBytes().length==prSn.length(); //无汉字
                if(isEnglish) {
                    Menu pr = menuService.findBySn(menu.getPsn());
                    if(pr!=null) {m.setPid(pr.getId());}
                } else {
                    String pinyin = PinyinToolkit.cn2Spell(prSn, "_");
                    Menu pr = menuService.findBySn(pinyin);
                    boolean isAdd = false;
                    if(pr==null) {pr = new Menu(); isAdd = true;}
                    pr.setDisplay(1); pr.setName(prSn); pr.setOrderNum(10);
                    pr.setSn(pinyin); pr.setPsn("root");
                    pr.setType("1"); pr.setHref("#");
                    if(isAdd) { menuService.save(pr); }
                    //else {this.update(pr);} //这里不作修改，只通过系统后台修改
                    menu.setPsn(pr.getSn()); menu.setPid(pr.getId());
                }
            }
        } catch (Exception e) {
        }
        if(m==null) {menuService.save(menu);}
        else {
            m.setDisplay(menu.getDisplay());
            m.setIcon(menu.getIcon());
            m.setName(menu.getName());
            //m.setOrderNum(menu.getOrderNum());
            m.setPsn(menu.getPsn());
            if(menu.getPid()!=null && menu.getPid()>0) { //
                m.setPid(menu.getPid());
            }
            m.setType(menu.getType());
            m.setHref(menu.getHref());
//            this.update(m);
            menuService.save(m);
        }
    }

    public void updateSort(Integer [] ids) {
        int index = 1;
//        String hql = "UPDATE Menu m set m.orderNum=? WHERE m.id=?";
        for(Integer id : ids) {
            em.createQuery("update Menu m set m.orderNum="+(index++)+" WHERE m.id="+id).executeUpdate();
        }
    }

    /**
     * 获取菜单数据，以JSON数据返回
     * @param type 菜单类型，1：导航菜单；2：权限菜单；其他：全部
     * @return
     */
    public String queryTreeJson(String type) {
        return buildTreeJson(null, type);
    }

    /**
     * 生成JSON数据
     * @param pid 父Id
     * @param type 类型
     * @return
     */
    private String buildTreeJson(Integer pid, String type) {
        List<Menu> plist = this.listByPid(pid, type);
        if(plist!=null && plist.size()>0) {
            Integer index = 0;
            StringBuffer sb = new StringBuffer();
            if(pid!=null && pid>0) {sb.append(",nodes:");}
            sb.append("[");
            for(Menu pm : plist) {
                index++;
                sb.append("{text:'<span title=").append(pm.getId()).append(">").append(pm.getName()).append("</span>', ")
                        .append("href:'javascript:targetHref(").append(pm.getId()).append(")'");

                //递归获取数据
                sb.append(buildTreeJson(pm.getId(), type));

                sb.append("}");
                if(index<plist.size()) {sb.append(",");}
            }
            sb.append("]");
            return sb.toString();
        } else {
            return "";
        }
    }

    /**
     * 通过父Id，及菜单类型获取菜单数据
     * @param pid 父Id,如果为空则获取最高层的菜单
     * @param type 菜单类型，1：导航菜单；2：权限菜单
     * @return
     */
    public List<Menu> listByPid(Integer pid, String type) {
        String hql = "FROM Menu m WHERE 1=1 ";
        if(pid!=null && pid>0) {hql += " AND m.pid="+pid;}
        else {hql += " AND m.pid is null ";}
        if(type!=null && ("1".equals(type) || "2".equals(type))) {
            hql += " AND m.type='"+type+"'";
        }
        hql += " ORDER BY m.orderNum ASC";
        return em.createQuery(hql).getResultList();
    }
}
