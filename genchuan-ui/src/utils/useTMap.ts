// src/utils/useTMap.ts
export const loadTMap = (): Promise<any> => {
  return new Promise((resolve) => {
    if ((window as any).TMap) {
      resolve((window as any).TMap)
      return
    }

    const existingScript = document.getElementById('tmap-gl-js')
    if (existingScript) {
      existingScript.addEventListener('load', () => resolve((window as any).TMap))
      return
    }

    const script = document.createElement('script')
    script.id = 'tmap-gl-js'
    script.src =
      'https://map.qq.com/api/gljs?libraries=tools&v=1.exp&key=ZOOBZ-TLBLT-M53X7-LPOVN-RNHKZ-Q5F5X'
    script.async = true
    script.onload = () => resolve((window as any).TMap)
    document.head.appendChild(script)
  })
}
