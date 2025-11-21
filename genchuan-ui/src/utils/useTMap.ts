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
      'https://map.qq.com/api/gljs?libraries=tools&v=1.exp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77'
    script.async = true
    script.onload = () => resolve((window as any).TMap)
    document.head.appendChild(script)
  })
}
