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