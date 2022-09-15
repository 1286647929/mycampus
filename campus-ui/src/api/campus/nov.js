import requet from '@/utils/request'


export function novdatalist(){
    return requet({
        url:'/campus/novdata/list',
        method:'get'
    })
}

export function novchinatotallist(){
    return requet({
        url:'/campus/novchinatotal/list',
        method:'get'
    })
}

export function novchinatotaltodaylist(){
    return requet({
        url:'/campus/novchinatotaltoday/list',
        method:'get'
    })
}

export function novlocaltotallist(){
    return requet({
        url: '/campus/novlocaltotal/list',
        method:'get'
    })
}