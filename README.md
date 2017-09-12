# MuSec
MuSec - Solução criada para tese de Mestrado de Engenharia Informática por Luís Sousa, 8090228@estg.ipp.pt.

A solução Music-enabled Security (MuSec), tem como objetivo ligar música e a segurança informática, através da sonorização de alertas que são detetados por um SIEM.
Tem como objetivo ajudar um administrador de rede a realizar outras tarefas do seu quotidiano enquanto consegue monitorizar a rede com alguma simplicidade. Para tal, o MuSec sonoriza eventos de rede gerados por um SIEM, neste caso usou-se o OSSIM. Aproveitam-se assim todas as funcionalidades e capacidades de monitorização do SIEM, em conjunto com todas as capacidades auditivas de um ser humano. Para isso a solução acede à base de dados do OSSIM para assim obter toda informação necessária sobre os vários eventos identificados pelo mesmo. A descrição dos eventos inclui informação como o risco, entre outros valores numéricos, que depois são utilizados para sonorizar esses eventos. Cada nível de risco foi mapeado num determinado som. Estes sons são loops musicais em formato wav que representam sons calmos e relaxados, em níveis de riscos baixos, ou sons mais agitados, que representam níveis altos de risco. A discrepância sonora entre esses sons, permite alertar eficientemente o administrador de rede, se algo está a afetar a rede ou não.


# Ecrã do Chuck e MuSec

![alt text](https://raw.githubusercontent.com/luis-sousa/MuSec/master/MusecChuck.png)


