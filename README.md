# MuSec
MuSec - Solu��o criada para tese de Mestrado de Engenharia Inform�tica por Lu�s Sousa, 8090228@estg.ipp.pt.

A solu��o Music-enabled Security (MuSec), tem como objetivo ligar m�sica e a seguran�a inform�tica, atrav�s da sonoriza��o de alertas que s�o detetados por um SIEM.
Tem como objetivo ajudar um administrador de rede a realizar outras tarefas do seu quotidiano enquanto consegue monitorizar a rede com alguma simplicidade. Para tal, o MuSec sonoriza eventos de rede gerados por um SIEM, neste caso usou-se o OSSIM. Aproveitam-se assim todas as funcionalidades e capacidades de monitoriza��o do SIEM, em conjunto com todas as capacidades auditivas de um ser humano. Para isso a solu��o acede � base de dados do OSSIM para assim obter toda informa��o necess�ria sobre os v�rios eventos identificados pelo mesmo. A descri��o dos eventos inclui informa��o como o risco, entre outros valores num�ricos, que depois s�o utilizados para sonorizar esses eventos. Cada n�vel de risco foi mapeado num determinado som. Estes sons s�o loops musicais em formato wav que representam sons calmos e relaxados, em n�veis de riscos baixos, ou sons mais agitados, que representam n�veis altos de risco. A discrep�ncia sonora entre esses sons, permite alertar eficientemente o administrador de rede, se algo est� a afetar a rede ou n�o.


# Ecr� do Chuck e MuSec

![alt text](https://raw.githubusercontent.com/luis-sousa/MuSec/master/MusecChuck.png)


