// 定义选项的类型接口
interface OptionItem {
    value: string | number;
    label: string;
}

interface OptionItem2 {
    value: string | number;
    key: string;
}

//处理选项类型数据 如：select radio  type 1是label value  2是用户配置nickname id
export const handleTypeOptions = (val: string | number, options: OptionItem[], type: string | number = 1) => {
    if (!options.length) {
        return ''
    }
    for (let i = 0; i < options.length; i++) {
        if(type==1){
            if (val == options[i].value) {
                return options[i].label
            }
        }else if(type==2){
            if (val == options[i].id) {
                return options[i].nickname
            }
        }


    }
    return ''
}

// 表格字段显示
export const tableFieldShow = (val: string | number, options: OptionItem2[]) => {
    if (!options) {
        return ''
    }
    if (!options.length) {
        return ''
    }
    for (let i = 0; i < options.length; i++) {
        if (val == options[i].key) {
            return options[i].value
        }
    }
    return ''
}



