import requet from '@/utils/request'

export function listNov(){
    return requet({
        url:'/campus/novdata/list',
        method:'get'
    })
}