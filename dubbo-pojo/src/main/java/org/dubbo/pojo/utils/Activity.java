package org.dubbo.pojo.utils;

import org.apache.log4j.Logger;
import org.dubbo.pojo.dto.activity.ActivityDto;
import org.dubbo.pojo.redis.RedisUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

public class Activity {

    public static RedisTemplate<String, Object> redisTemplate;

    public static void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        Activity.redisTemplate = redisTemplate;
    }

    private static final Logger logger = Logger.getLogger(Activity.class);

    public static <T> List<String> getPoolList(Map<T,Integer> poolSize){
        //初始化奖池数量
        List<T> myList = new ArrayList<T>();

        for(T key : poolSize.keySet()){
            Integer length = poolSize.get(key);
            for(int i =0;i<length;i++){
                myList.add(key);
            }
        }
        //随机读取奖项填入堆栈
        Stack<T> mystack = new Stack<T>();
        mystack = randomList(myList);
        //将堆栈数据拼接序号，填入列表
        List<String> poolList = new ArrayList<String>();
        int index = 0;
        while(!mystack.isEmpty()){
            index++;
            T i = mystack.pop();
            poolList.add(i+"_"+index);
        }
        return poolList;
    }

    /**
     * 打乱list压入stack
     * @param list
     * @return
     */
    public static <T> Stack<T> randomList(List<T> list){
        Stack<T> stack = new Stack<T>();
        while(list.size()>0){
            int random = (int) (Math.random() * list.size());
            stack.add(list.get(random));
            list.remove(random);
        }
        return stack;
    }

    public static <T> boolean initLotteryPool(Map<T,Integer> map, RedisUtil jedis, String redisListName){
        long start = System.currentTimeMillis();
        List<String> pool= Activity.getPoolList(map);
        System.out.println(pool);
        Map<String,Integer> resMap= new TreeMap<String,Integer>();
        for(String res : pool){
            if(resMap.get(res.split("_")[0])==null){
                resMap.put(res.split("_")[0], 1);
            }else{
                resMap.put(res.split("_")[0],resMap.get(res.split("_")[0])+1);
            }
        }
        for(T key : map.keySet()){
            if(resMap.get(String.valueOf(key)).intValue() !=(map.get(key)).intValue()){
                logger.info("奖池初始化"+key+"等奖个数异常，重新初始化");
            }
        }
        System.out.println(resMap);
//        jedis.lSet(redisListName,(String[])pool.toArray(new String[pool.size()]));
        jedis.lSet(redisListName,(String[])pool.toArray(new String[pool.size()]));
//        redisTemplate.opsForList().leftPushAll(redisListName,(String[])pool.toArray(new String[pool.size()]));
        logger.info("本次初始化奖池 耗时："+(System.currentTimeMillis() - start));
        return true;
    }

    public static String getPoolKey(ActivityDto activityDto){
        return activityDto.getActivityId()+"_"+activityDto.getUnionId();
    }

}
