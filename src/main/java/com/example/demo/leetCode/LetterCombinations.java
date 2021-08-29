package com.example.demo.leetCode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class LetterCombinations {
    public static void main(String[] args) {
        System.out.println(letterCombinations("2345"));
    }


    public static List<String> letterCombinations(String digits) {
        Map<String,String[]> map = new HashMap<>();
        map.put("1",new String[]{""});
        map.put("2",new String[]{"a","b","c"});
        map.put("3",new String[]{"d","e","f"});
        map.put("4",new String[]{"g","h","i"});
        map.put("5",new String[]{"j","k","l"});
        map.put("6",new String[]{"m","n","o"});
        map.put("7",new String[]{"p","q","r","s"});
        map.put("8",new String[]{"t","u","v"});
        map.put("9",new String[]{"w","x","y","z"});
        List<String> list = new ArrayList();
        if(digits==null||digits.length()==0){
            return list;
        }
        char[] chars = digits.toCharArray();
        String[] strings = new String[chars.length];
        for(int i = 0;i<strings.length;i++){
            strings[i] = chars[i]+"";
        }
        //return bfs(strings,map,list);
        return dfs(strings,map,0,"",list);
    }

    /**
     * 深度优先
     * @return
     */
    public static List<String> dfs(String[] chars,Map<String,String[]> map,int bigIndex,String s,List<String> arrys){
        if(bigIndex==chars.length){
            arrys.add(s);
        } else{
            String[] strings = map.get(chars[bigIndex]);
            for(int i = 0;i<strings.length;i++){
                s += strings[i];
                dfs(chars,map,bigIndex+1,s,arrys);
                s = s.substring(0,s.length()-1);
            }
        }
        return arrys;
    }

    /**
     * 广度优先
     * @return
     */
    public static List<String> bfs(String[] chars,Map<String,String[]> map,List<String> arrys){
        for(int i = 0;i<chars.length;i++){
             String[] strings = map.get(chars[i]);
             if(arrys.size()==0){
                 for(int j = 0;j<strings.length;j++){
                     arrys.add(strings[j]);
                 }
             }else{
                 List<String>  newlist = new ArrayList<>();
                 for(int j = 0;j<arrys.size();j++){
                     for(int k = 0;k<strings.length;k++){
                         newlist.add(arrys.get(j)+strings[k]);
                     }
                 }
                 arrys = newlist;
             }

         }

        return arrys;
    }

}
